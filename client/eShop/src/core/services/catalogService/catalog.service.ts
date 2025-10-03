import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  readonly #uri : string = "http://10.36.28.31:9999/";

  readonly #http = inject(HttpClient);

  constructor() {

  }
}
