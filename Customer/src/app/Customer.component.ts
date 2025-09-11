import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerService } from './Customer.service';

// Define the Customer interface to enforce structure
export interface Customer {
  id: number | null;
  name: string;
  orderId: number;
  email: string;
  phone: string;
}

@Component({
  selector: 'app-customer-root',
  templateUrl: './Customer.component.html',
  styleUrls: ['./Customer.component.scss']
})
export class CustomerComponent implements OnInit {
  title = 'customers';
  customers: Customer[] = [];
  customerToUpdate: Customer = this.getEmptyCustomer();

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  /**
   * Creates a blank customer object
   */
  private getEmptyCustomer(): Customer {
    return {
      id: null,
      name: '',
      orderId: 0,
      email: '',
      phone: ''
    };
  }

  /**
   * Registers a new Customer using the provided form data.
   */
  register(registerForm: NgForm): void {
    if (registerForm.invalid) {
      alert('Please fill in all required fields.');
      return;
    }

    this.customerService.registerCustomer(registerForm.value).subscribe(
      () => {
        registerForm.reset();
        this.getCustomers(); // Refresh customer list
      },
      error => {
        console.error('Error registering customer:', error);
        alert('There was an error registering the customer.');
      }
    );
  }

  /**
   * Fetches all customers from the backend and updates the local state.
   */
  getCustomers(): void {
    this.customerService.getCustomers().subscribe(
      (response: Customer[]) => {
        this.customers = response;
        console.log('Loaded customers:', this.customers);
      },
      error => {
        console.error('Error fetching customers:', error);
        alert('Failed to fetch customers.');
      }
    );
  }

  /**
   * Deletes a customer by ID.
   */
  deleteCustomer(customer: Customer): void {
    if (!customer.id) {
      console.error('Customer ID is undefined. Cannot delete customer.', customer);
      return;
    }
    if (!confirm(`Are you sure you want to delete customer: ${customer.name}?`)) return;

    this.customerService.deleteCustomer(customer.id).subscribe(
      () => {
        console.log('Customer deleted successfully.');
        this.getCustomers(); // Refresh customer list
      },
      error => {
        console.error('Error deleting customer:', error);
        alert('Error deleting customer.');
      }
    );
  }

  /**
   * Sets the customerToUpdate object for editing.
   */
  edit(customer: Customer): void {
    this.customerToUpdate = { ...customer };
  }

  /**
   * Updates an existing customer with the current customerToUpdate values.
   */
  updateCustomer(): void {
    if (!this.customerToUpdate.id) {
      console.error('Customer ID is undefined. Cannot update customer.');
      alert('Customer ID is undefined. Cannot update customer.');
      return;
    }

    this.customerService.updateCustomer(this.customerToUpdate).subscribe(
      () => {
        console.log('Customer updated successfully.');
        this.getCustomers(); // Refresh customer list
        this.customerToUpdate = this.getEmptyCustomer(); // Reset after update
      },
      error => {
        console.error('Error updating customer:', error);
        alert('Error updating customer.');
      }
    );
  }
}
