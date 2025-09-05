import { Component, Input } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { Task } from '../../models/task.model'

@Component({
  selector: 'app-quadrant',
  templateUrl: './quadrant.component.html'
})
export class QuadrantComponent {
  @Input() quadrant!: number;
  @Input() title!: string;
  @Input() color!: string;
  @Input() icon!: string;
  tasks: Task[] = [];

  constructor(public taskService: TaskService) {}

  ngOnInit() {
    this.taskService.tasks$.subscribe(tasks => {
      this.tasks = this.taskService.getTasksByQuadrant(this.quadrant);
    });
  }

  toggleComplete(taskId: number) {
    this.taskService.toggleComplete(taskId);
  }

  deleteTask(taskId: number) {
    if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
      this.taskService.deleteTask(taskId);
    }
  }

  isOverdue(dueDate: string): boolean {
    const due = new Date(dueDate);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    return due < today;
  }
}