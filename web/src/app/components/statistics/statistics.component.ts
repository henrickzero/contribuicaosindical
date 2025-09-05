import { Component } from '@angular/core';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html'
})
export class StatisticsComponent {
  constructor(public taskService: TaskService) {}
}