export interface Task {
  id: number;
  title: string;
  due?: string;
  desc?: string;
  quadrant: number;
  completed: boolean;
  createdAt: string;
  importance: string;
  urgency: string;
}