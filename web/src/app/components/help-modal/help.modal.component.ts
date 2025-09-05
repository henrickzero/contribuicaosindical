import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-help-modal',
  templateUrl: './help.modal.component.html'
})
export class HelpModalComponent {
  @Input() showModal = false;
  @Output() closeModal = new EventEmitter<void>();
}