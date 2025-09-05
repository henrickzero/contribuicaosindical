import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-modal-form',
  templateUrl: './task.modal.component.html'
})
export class TaskModalComponent {
  @Input() showModal = false;
  @Output() closeModal = new EventEmitter<void>();

  task: Task = {
    id: 0,
    title: '',
    quadrant: 1,
    completed: false,
    createdAt: '',
    importance: 'important',
    urgency: 'urgent'
  };

  constructor(private taskService: TaskService) {}

  onSubmit() {
    this.task.id = Date.now();
    this.task.createdAt = new Date().toISOString();

    if (this.task.importance === 'important' && this.task.urgency === 'urgent') {
      this.task.quadrant = 1;
    } else if (this.task.importance === 'important' && this.task.urgency === 'not-urgent') {
      this.task.quadrant = 2;
    } else if (this.task.importance === 'not-important' && this.task.urgency === 'urgent') {
      this.task.quadrant = 3;
    } else {
      this.task.quadrant = 4;
    }

    this.taskService.addTask({ ...this.task });
    alert('Tarefa adicionada com sucesso!');
    this.closeModal.emit();
    this.task = {
      id: 0,
      title: '',
      quadrant: 1,
      completed: false,
      createdAt: '',
      importance: 'important',
      urgency: 'urgent'
    };
  }
}