import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

export interface SankeyData {
  id?: string;
  source: string;
  target: string;
  value: number;
}

@Injectable({
  providedIn: 'root'
})
export class SankeyDataService {
  private apiUrl = environment.BASE_URL+'/api/v1/sankey-data'; // Replace with your actual API URL
  private sankeyData: SankeyData[] = [];
  private sankeyDataSubject = new BehaviorSubject<SankeyData[]>(this.sankeyData);
  sankeyData$ = this.sankeyDataSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadSankeyData();
  }

  private loadSankeyData(): void {
    this.http.get<SankeyData[]>(this.apiUrl).pipe(
      catchError(this.handleError),
      tap(data => {
        this.sankeyData = data;
        this.sankeyDataSubject.next(this.sankeyData);
      })
    ).subscribe();
  }

  addSankeyData(data: SankeyData): Observable<SankeyData> {
    return this.http.post<SankeyData>(this.apiUrl, data).pipe(
      catchError(this.handleError),
      tap(newData => {
        this.sankeyData.push(newData);
        this.sankeyDataSubject.next(this.sankeyData);
      })
    );
  }

  updateSankeyData(data: SankeyData): Observable<SankeyData> {
    return this.http.put<SankeyData>(`${this.apiUrl}/${data.id}`, data).pipe(
      catchError(this.handleError),
      tap(updatedData => {
        const index = this.sankeyData.findIndex(d => d.id === updatedData.id);
        if (index !== -1) {
          this.sankeyData[index] = updatedData;
          this.sankeyDataSubject.next(this.sankeyData);
        }
      })
    );
  }

  deleteSankeyData(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError),
      tap(() => {
        this.sankeyData = this.sankeyData.filter(d => d.id !== id);
        this.sankeyDataSubject.next(this.sankeyData);
      })
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }

  fetchSankeyData(): Observable<SankeyData[]> {
    // Replace with your actual API endpoint
    return this.http.get<SankeyData[]>('your-api-endpoint/sankey-data').pipe(
      tap(data => {
        this.sankeyDataSubject.next(data);
      })
    );
  }
}