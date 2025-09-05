import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SankeyDataService, SankeyData } from 'src/app/service/sankey-data.service';
import { GlobalService } from 'src/app/service/global.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sankey-data',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './sankey-data.component.html',
  styleUrls: ['./sankey-data.component.css']
})
export class SankeyDataComponent implements OnInit {
  sankeyData: SankeyData[] = [];
  filteredData: SankeyData[] = [];
  newRow: SankeyData = {
    source: '',
    target: '',
    value: 0
  };
  editIndex: number | null = null;
  filterSource: string = '';
  filterTarget: string = '';

  constructor(
    private sankeyDataService: SankeyDataService,
    private globalService: GlobalService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    globalService.setAppName('Sankey - Gestão');
  }

  ngOnInit(): void {
    this.sankeyDataService.sankeyData$.subscribe(data => {
      this.sankeyData = data;
      this.applyFilters(); // Apply filters when data changes
    });
  }

  applyFilters(): void {
    this.filteredData = this.sankeyData.filter(row => {
      const matchesSource = !this.filterSource || row.source.toLowerCase().includes(this.filterSource.toLowerCase());
      const matchesTarget = !this.filterTarget || row.target.toLowerCase().includes(this.filterTarget.toLowerCase());
      return matchesSource && matchesTarget;
    });
  }

  saveData() {
    if (this.editIndex !== null) {
      const updatedData = { ...this.newRow, id: this.sankeyData[this.editIndex].id };
      this.sankeyDataService.updateSankeyData(updatedData).subscribe(() => {
        this.editIndex = null;
        this.resetForm();
      });
    } else {
      this.sankeyDataService.addSankeyData(this.newRow).subscribe(() => {
        this.resetForm();
      });
    }
  }

  editRow(index: number) {
    this.editIndex = index;
    const row = this.filteredData[index];
    this.newRow = { ...row };
  }

  deleteRow(index: number) {
    if (confirm('Are you sure you want to delete this row?')) {
      const id = this.filteredData[index].id;
      if (id) {
        this.sankeyDataService.deleteSankeyData(id).subscribe(() => {
          this.resetForm();
        });
      }
    }
  }

  cancelEdit() {
    this.editIndex = null;
    this.resetForm();
  }

    toSankey() {
      this.router.navigate(['sankey']);
  }


  resetForm() {
    this.newRow = {
      source: '',
      target: '',
      value: 0
    };
  }
}