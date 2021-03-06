import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './categories/categories.component';
import { CreateCategoryComponent } from './categories/create-category/create-category.component';
import { DetailCategoriesComponent } from './categories/detail-categories/detail-categories.component';
import { ListCategoriesComponent } from './categories/list-categories/list-categories.component';
import { ListCustomersComponent } from './customers/list-customers/list-customers.component';
import { DashbordComponent } from './dashbord/dashboard/dashbord.component';
import { ListOrdersComponent } from './orders/list-orders/list-orders.component';
import { CreateProductComponent } from './products/create-product/create-product.component';
import { ListProductsComponent } from './products/list-products/list-products.component';
import { ProductDetailComponent } from './products/product-detail/product-detail.component';
import { ProductsComponent } from './products/products.component';
import { ListPromotionsComponent } from './promotions/list-promotions/list-promotions.component';

const routes: Routes = [
  {path: '' , redirectTo: '/dashbord' , pathMatch: 'full'},
  {path: 'dashbord' , component: DashbordComponent},
  {path: 'admin' , component: CategoriesComponent , children: [
    {path: 'listCategories' , component: ListCategoriesComponent},
    {path: 'createCategory' , component: CreateCategoryComponent},
    {path: 'detailCategory/:id' , component: DetailCategoriesComponent},
    {path: 'updateCategory/:id' , component: CreateCategoryComponent}
  ]},
  {path: 'admin' , component: ProductsComponent , children: [
    {path: 'listProducts' , component: ListProductsComponent},
    {path: 'createProduct' , component: CreateProductComponent},
    {path: 'detailProduct/:categoryId/:promotionId/:id' , component: ProductDetailComponent},
    {path: 'updateProduct/:categoryId/:promotionId/:id' , component: CreateProductComponent}
  ]},
  {path: 'listProducts' , component: ListProductsComponent},
  {path: 'listCustomers' , component: ListCustomersComponent},
  {path: 'listPromotions' , component: ListPromotionsComponent},
  {path: 'listOrders' , component: ListOrdersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
