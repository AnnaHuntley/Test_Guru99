import HomePage1 from '../page-objects/homePage';

const HomePage2 = new HomePage1();

describe('Rahul Shetty Angular Practice', () => {
  it('POM Sample Test case', () => {
    HomePage2.openHomePage();
    HomePage2.typeNameInTextBox();
    HomePage2.selectGender();

    HomePage2.verifyDataBindingTextBox();
    HomePage2.verifyMinimumLengthofTextBox();
    HomePage2.verifyCheckboxIsDisabled();
  });
});

/*

Step 1 = Visit homepage
Step 2 = Set Name = David
Step 3 = Set Gender = Male

Step 4 = Verification Of Data Binding Example
Step 5 = Verify Minimum Lenght of Name is 2 Characters
Step 6 = Verify if the Entrepreneur button is  disabled

*/
