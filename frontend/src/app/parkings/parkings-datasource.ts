import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import {MatTableDataSource} from '@angular/material/table';


// TODO: Replace this with your own data model type
export interface ParkingsItem {
  name: string;
  address: string;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: ParkingsItem[] = [
  {address: "Obolon", name: 'Parking1'},
  {address: "Poznyaky", name: 'Parking2'},
  {address: "Vozdvyzhenka", name: 'Parking3'},
  {address: "Pechersk", name: 'Parking4'},
  {address: "Borshaga", name: 'Parking5'},
  {address: "Podol", name: 'Parking6'},
  {address: "Troya", name: 'Parking7'},
  {address: "Darnitsa", name: 'Parking8'},
  {address: "Soloma", name: 'Parking9'},
  {address: "Svyat", name: 'Parking10'},
  {address: "Hydropark", name: 'Parking11'},
  {address: "Borshaga", name: 'Parking5'},
  {address: "Podol", name: 'Parking6'},
  {address: "Troya", name: 'Parking7'},
  {address: "Darnitsa", name: 'Parking8'},
  {address: "Soloma", name: 'Parking9'},
  {address: "Svyat", name: 'Parking10'},
  {address: "Hydropark", name: 'Parking11'}
];

/**
 * Data source for the Parkings view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ParkingsDataSource extends DataSource<ParkingsItem> {
  data: ParkingsItem[] = EXAMPLE_DATA;
  paginator: MatPaginator;
  sort: MatSort;
  dataSource = new MatTableDataSource(EXAMPLE_DATA );

  // applyFilter(filterValue: string) {
  //   this.data.filter(callbackfn: (value: ParkingsItem, index: number, array: ParkingsItem[]) => 
  //   value is ParkingsItem, thisArg?: any): ParkingsItem[]);
  // }

  constructor() {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<ParkingsItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: ParkingsItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: ParkingsItem[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(+a.address, +b.address, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}


