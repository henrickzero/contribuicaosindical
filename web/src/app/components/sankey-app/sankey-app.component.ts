import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SankeyDataService, SankeyData } from 'src/app/service/sankey-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './sankey-app.component.html',
  styleUrls: ['./sankey-app.component.css']
})
export class SankeyAppComponent implements OnInit {
  showModal = false;
  showTaskModal = false;

  sankeyData: Array<[string, string, number]> = [];
  sankeyOptions: any;

  constructor(
    private router: Router,
    private sankeyDataService: SankeyDataService
  ) {
    this.sankeyOptions = {
      width: 1200,
      height: 800,
      sankey: {
        node: {
          colors: [], // Will be populated dynamically
          label: {
            fontName: 'Arial Black',
            fontSize: 11,
            color: '#333',
            fontWeight: 'bold' // Set labels to bold
          },
          interactivity: true
        },
        link: {
          colorMode: 'gradient',
          colors: [] // Will be populated dynamically
        }
      }
    };
  }

  ngOnInit(): void {
   
    this.sankeyDataService.sankeyData$.subscribe(data => {
      this.sankeyData = data.map(row => [row.source, row.target, row.value]);
      this.updateSankeyOptions();    
      
    });
  }

  private updateSankeyOptions(): void {
    // Extract unique nodes
    const uniqueNodes = Array.from(
      new Set(this.sankeyData.flatMap(row => [row[0], row[1]]))
    );

    // Generate colors for nodes
    const nodeColors = this.generateColors(uniqueNodes.length);

    // Generate colors for links
    const linkColors = this.generateColors(this.sankeyData.length);

    // Update sankeyOptions
    this.sankeyOptions = {
      ...this.sankeyOptions,
      sankey: {
        ...this.sankeyOptions.sankey,
        node: {
          ...this.sankeyOptions.sankey.node,
          colors: nodeColors
        },
        link: {
          ...this.sankeyOptions.sankey.link,
          colors: linkColors
        }
      }
    };
    
  }

  private generateColors(count: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < count; i++) {
      // Generate HSL-based color and convert to hex
      const hue = (i * 360) / count; // Spread hues evenly across 360 degrees
      const saturation = 70; // Fixed saturation for vibrant colors
      const lightness = 50; // Fixed lightness for visibility
      const hexColor = this.hslToHex(hue, saturation, lightness);
      colors.push(hexColor);
    }
    return colors;
  }

  private hslToHex(h: number, s: number, l: number): string {
    l /= 100;
    const a = s * Math.min(l, 1 - l) / 100;
    const f = (n: number) => {
      const k = (n + h / 30) % 12;
      const color = l - a * Math.max(Math.min(k - 3, 9 - k, 1), -1);
      return Math.round(255 * color).toString(16).padStart(2, '0');
    };
    return `#${f(0)}${f(8)}${f(4)}`;
  }

  navigateToHome() {
    this.router.navigate(['/xxxx']);
  }

  navigateToEdit() {
    this.router.navigate(['/editsankey']);
  }
}