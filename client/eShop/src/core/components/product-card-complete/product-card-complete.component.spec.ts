import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCardCompleteComponent } from './product-card-complete.component';

describe('ProductCardCompleteComponent', () => {
  let component: ProductCardCompleteComponent;
  let fixture: ComponentFixture<ProductCardCompleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductCardCompleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductCardCompleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
