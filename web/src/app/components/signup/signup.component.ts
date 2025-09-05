import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, User } from 'src/app/service/auth.service';
import { GlobalService } from 'src/app/service/global.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm: FormGroup;
  isLoading = false;
  isSuccess = false;
  @ViewChild('signupFormElement', { static: false }) signupFormElement?: ElementRef;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private globalService: GlobalService
  ) {
    globalService.setAppName('Cadastro');
    this.signupForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  // Custom validator to check if passwords match
  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      this.triggerShakeAnimation();
      return;
    }

    this.isLoading = true;
    const user: User = this.signupForm.value;

    this.authService.register(user).subscribe({
      next: () => {
        this.isSuccess = true;
        setTimeout(() => {
          this.isLoading = false;
          this.isSuccess = false;
          this.router.navigate(['/']);
        }, 1500);
      },
      error: (error) => {
        console.error('Registration failed', error);
        this.isLoading = false;
        this.triggerShakeAnimation();
      }
    });
  }

  private triggerShakeAnimation() {
    if (this.signupFormElement && this.signupFormElement.nativeElement) {
      const form = this.signupFormElement.nativeElement;
      form.classList.add('shake');
      setTimeout(() => form.classList.remove('shake'), 500);
    } else {
      console.warn('signupFormElement is not available');
    }
  }
}