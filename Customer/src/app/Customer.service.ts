import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from './Customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  API = "http://localhost:8087/customerdetail";

  constructor(private http: HttpClient) {}

  /** Register a new customer */
  public registerCustomer(customer: Customer) {
    return this.http.post(this.API, customer);
  }

  /** Fetch all customers */
  public getCustomers() {
    return this.http.get<Customer[]>(this.API);
  }

  /** Delete customer by ID */
  public deleteCustomer(customerId: number) {
    return this.http.delete(`${this.API}/${customerId}`);
  }

  /** Update existing customer */
  public updateCustomer(customer: Customer) {
    if (!customer.id) {
      throw new Error('Customer ID is required for updating customer data.');
    }
    return this.http.put(`${this.API}/${customer.id}`, customer);
  }
}
