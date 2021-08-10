import {Category} from './category';
import {Promotion} from './promotion';
import {MyBrand} from '../enums/myBrand';
import {MyGeneration} from '../enums/myGeneration';

export interface Product {
  productId: number,
  productName: string,
  brand: MyBrand,
  model: string,
  price: number,
  description: string,
  image: Blob,
  category: Category;
  promotion: Promotion;
}
