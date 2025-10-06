import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCardBriefComponent } from './product-card-brief.component';

describe('ProductCardBriefComponent', () => {
  let component: ProductCardBriefComponent;
  let fixture: ComponentFixture<ProductCardBriefComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductCardBriefComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductCardBriefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
