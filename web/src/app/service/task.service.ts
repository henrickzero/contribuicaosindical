import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Task } from '../models/task.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = environment.BASE_URL+'/api/v1/tasks'; // Replace with your actual API URL
  private tasks: Task[] = [];
  private tasksSubject = new BehaviorSubject<Task[]>(this.tasks);
  tasks$ = this.tasksSubject.asObservable();

  constructor(private http: HttpClient) {
    // Fetch tasks on service initialization
    this.loadTasks();
  }

  // Load tasks from the web service
  private loadTasks(): void {
    this.http.get<Task[]>(this.apiUrl).pipe(
      catchError(this.handleError),
      tap(tasks => {
        this.tasks = tasks;
        this.tasksSubject.next(this.tasks);
      })
    ).subscribe();
  }

  // Save a single task to the backend (used for add and update)
  private saveTaskToServer(task: Task): Observable<Task> {
    if (task.id) {
      // Update existing task
      return this.http.put<Task>(`${this.apiUrl}/${task.id}`, task).pipe(
        catchError(this.handleError)
      );
    } else {
      // Create new task
      return this.http.post<Task>(this.apiUrl, task).pipe(
        catchError(this.handleError)
      );
    }
  }

  // Save tasks to the server and update local state
  private saveTasks(): void {
    // Since we're using HTTP, we don't need to save all tasks at once.
    // Instead, individual CRUD operations will handle updates.
    this.tasksSubject.next(this.tasks);
  }

  addTask(task: Task): void {
    this.saveTaskToServer(task).pipe(
      tap(newTask => {
        this.tasks.push(newTask);
        this.saveTasks();
      })
    ).subscribe();
  }

  toggleComplete(taskId: number): void {
    const taskIndex = this.tasks.findIndex(t => t.id === taskId);
    if (taskIndex !== -1) {
      const updatedTask = { ...this.tasks[taskIndex], completed: !this.tasks[taskIndex].completed };
      this.saveTaskToServer(updatedTask).pipe(
        tap(() => {
          this.tasks[taskIndex] = updatedTask;
          this.saveTasks();
        })
      ).subscribe();
    }
  }

  deleteTask(taskId: number): void {
    this.http.delete(`${this.apiUrl}/${taskId}`).pipe(
      catchError(this.handleError),
      tap(() => {
        this.tasks = this.tasks.filter(t => t.id !== taskId);
        this.saveTasks();
      })
    ).subscribe();
  }

  getTasksByQuadrant(quadrant: number): Task[] {
    return this.tasks
      .filter(t => t.quadrant === quadrant)
      .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
  }

  getQuadrantCount(quadrant: number): number {
    return this.tasks.filter(t => t.quadrant === quadrant).length;
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
  }

  // Error handling
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}