import { TestBed, inject } from '@angular/core/testing';

import { DetailsDialogService } from './details-dialog.service';

describe('DetailsDialogService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DetailsDialogService]
    });
  });

  it('should be created', inject([DetailsDialogService], (service: DetailsDialogService) => {
    expect(service).toBeTruthy();
  }));
});
