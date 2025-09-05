import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { GlobalService } from 'src/app/service/global.service';
import { SankeyDataService } from 'src/app/service/sankey-data.service';

declare var google: any;

@Component({
  selector: 'app-sankey-chart',
  templateUrl: './sankey.component.html',
  styleUrls: ['./sankey.component.css']
})
export class SankeyChartComponent implements OnInit, OnChanges {
  @Input() chartData: any[] = [];
  @Input() chartOptions: any = {};

  constructor(
    private globalService: GlobalService,
    private sankeyDataService: SankeyDataService
  ) {
    globalService.setAppName('Sankey');
  }

  ngOnInit() {
    google.charts.load('current', { packages: ['sankey'] });
    google.charts.setOnLoadCallback(() => {
      this.drawChart();
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    // Redraw the chart when chartData or chartOptions change
    if ((changes['chartData'] && changes['chartData'].currentValue) || 
        (changes['chartOptions'] && changes['chartOptions'].currentValue)) {
      // Ensure Google Charts is loaded before drawing
      if (google.visualization) {
        this.drawChart();
      } else {
        google.charts.load('current', { packages: ['sankey'] });
        google.charts.setOnLoadCallback(() => {
          this.drawChart();
        });
      }
    }
  }

  drawChart() {
    const data = new google.visualization.DataTable();
    data.addColumn('string', 'From');
    data.addColumn('string', 'To');
    data.addColumn('number', 'Weight');

    // Use input data if provided, otherwise fall back to empty array
    const rows = this.chartData.length > 0 ? this.chartData : [];

    data.addRows(rows);

    // Use input options if provided, otherwise fall back to default
    const options = this.chartOptions || {
      width: 1200,
      height: 800,
      sankey: {
        node: {
          colors: ['#a6cee3', '#b2df8a', '#fb9a99'],
          label: {
            fontName: 'Arial',
            fontSize: 10,
            color: '#333'
          },
          interactivity: true
        },
        link: {
          colorMode: 'gradient',
          colors: ['#a6cee3', '#b2df8a']
        }
      }
    };

    const chart = new google.visualization.Sankey(document.getElementById('sankey_chart'));
    chart.draw(data, options);
  }
}