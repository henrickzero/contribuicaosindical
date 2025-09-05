import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { GlobalService } from 'src/app/service/global.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  isSuccess = false;
  @ViewChild('loginFormElement', { static: false }) loginFormElement?: ElementRef;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private globalService: GlobalService
  ) {
    globalService.setAppName('Login');
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.triggerShakeAnimation();
      return;
    }

    this.isLoading = true;
    const { email, password } = this.loginForm.value;

    this.authService.login(email, password).subscribe({
      next: () => {
        this.isSuccess = true;
        setTimeout(() => {
          this.isLoading = false;
          this.isSuccess = false;
          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl]);
        }, 1500);
      },
      error: (error) => {
        console.error('Login failed', error);
        this.isLoading = false;
        this.triggerShakeAnimation();
      }
    });
  }

  socialLogin(provider: string) {
    console.log(`Social login with ${provider}`);
    // Implement social login logic (e.g., OAuth with backend endpoint)
  }

  private triggerShakeAnimation() {
    if (this.loginFormElement && this.loginFormElement.nativeElement) {
      const form = this.loginFormElement.nativeElement;
      form.classList.add('shake');
      setTimeout(() => form.classList.remove('shake'), 500);
    } else {
      console.warn('loginFormElement is not available');
    }
  }
}