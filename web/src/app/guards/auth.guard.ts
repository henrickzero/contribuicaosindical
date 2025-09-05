import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { jwtDecode } from 'jwt-decode'; // Import jwt-decode

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const token = this.authService.getToken();
    if (token && this.isTokenValid(token)) {
      this.authService.isAuthenticated(); // Ensure state is updated
      return true; // Allow access
    }

    // Redirect to login and store the attempted URL for redirect after login
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }

  private isTokenValid(token: string): boolean {
    try {
      const decodedToken: any = jwtDecode(token);
      const currentTime = Date.now() / 1000; // Current time in seconds
      if (decodedToken.exp && decodedToken.exp < currentTime) {
        // Token is expired
        this.authService.logout(); // Clear invalid token
        return false;
      }
      return true; // Token is valid
    } catch (error) {
      console.error('Invalid token', error);
      this.authService.logout(); // Clear invalid token
      return false;
    }
  }
}