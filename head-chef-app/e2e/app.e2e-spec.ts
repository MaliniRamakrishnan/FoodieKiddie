import { HeadChefAppPage } from './app.po';

describe('head-chef-app App', () => {
  let page: HeadChefAppPage;

  beforeEach(() => {
    page = new HeadChefAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
