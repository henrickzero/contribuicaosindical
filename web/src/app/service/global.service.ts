// src/app/services/global.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root' // Makes the service a singleton, available app-wide
})
export class GlobalService {
  // Example global variables
  private appNameSubject = new BehaviorSubject<string>('Home');
  private apiUrlSubject = new BehaviorSubject<string>('https://api.myapp.com');
  private currentUserSubject = new BehaviorSubject<{ email: string; fullName?: string } | null>(null);

  // Observables for components to subscribe to
  appName$ = this.appNameSubject.asObservable();
  apiUrl$ = this.apiUrlSubject.asObservable();
  currentUser$ = this.currentUserSubject.asObservable();

  // Getters
  get appName(): string {
    return this.appNameSubject.value;
  }

  get apiUrl(): string {
    return this.apiUrlSubject.value;
  }

  get currentUser(): { email: string; fullName?: string } | null {
    return this.currentUserSubject.value;
  }

  // Setters
  setAppName(name: string) {
    this.appNameSubject.next(name);
  }

  setApiUrl(url: string) {
    this.apiUrlSubject.next(url);
  }

  setCurrentUser(user: { email: string; fullName?: string } | null) {
    this.currentUserSubject.next(user);
  }
}