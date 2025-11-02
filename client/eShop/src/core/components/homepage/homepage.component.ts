import { Component, computed, inject, signal } from '@angular/core';
import { CatalogService } from '../../services/catalogService/catalog.service';
import { ProductCardBriefComponent } from "../product-card-brief/product-card-brief.component";
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [ProductCardBriefComponent, RouterLink],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {
  service = inject(CatalogService)
}
