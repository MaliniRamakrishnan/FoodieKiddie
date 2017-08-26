import { ChefAppPage } from './app.po';

describe('chef-app App', () => {
  let page: ChefAppPage;

  beforeEach(() => {
    page = new ChefAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
