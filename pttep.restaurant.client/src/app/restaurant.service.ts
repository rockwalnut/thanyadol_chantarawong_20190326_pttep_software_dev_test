import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Restaurant } from './restaurant';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class RestaurantService {

  private restaurantesUrl = 'http://127.0.0.1:8080/api/search_restaurants';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }


  /* GET restaurantes whose name contains search term */
  searchRestaurantes(term: string): Observable<Restaurant[]> {
    if (!term.trim()) {
      // if not search term, return empty restaurant array.
      return of([]);
    }
    return this.http.get<Restaurant[]>(`${this.restaurantesUrl}?query=${term}`).pipe(
      tap(_ => this.log(`found restaurantes matching "${term}"`)),
      catchError(this.handleError<Restaurant[]>('searchRestaurantes', []))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a RestaurantService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`RestaurantService: ${message}`);
  }
}
