import { Injectable } from "@angular/core";

export interface Menu {
    state: string;
    name: string;
    type: string;
    icon: string;
    role: string;
  }
  
  const MENUITEMS = [
    //Admin
    { state: 'dashboard', name: 'Dashboard', type: 'link', icon: 'dashboard',role: '' },
    { state: 'category', type: 'link', name: 'Manage Category', icon: 'category',role: 'Admin' },
    { state: 'product', type: 'link', name: 'Manage Product', icon: 'inventory_2',role: 'Admin' },
    { state: 'order', type: 'link', name: 'Manage Order', icon: 'shopping_cart',role: 'Admin' },
    { state: 'bill', type: 'link', name: 'View Bill', icon: 'backup_table',role: 'Admin' },
    { state: 'user', type: 'link', name: 'Manage Users', icon: 'people',role: 'Admin' },
    //User
    { state: 'order', type: 'link', name: 'Manage Order', icon: 'shopping_cart',role: 'User' },
    { state: 'bill', type: 'link', name: 'View Bill', icon: 'backup_table',role: 'User' },
    { state: 'buyproduct', type: 'link', name: 'Buy Product', icon: 'payment',role: 'User' }
  ];
  
  @Injectable()
  export class MenuItems {
    getMenuitem(): Menu[] {
      return MENUITEMS;
    }
  }