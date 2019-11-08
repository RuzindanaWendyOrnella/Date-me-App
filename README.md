### Date-me-App

### Description
This is an Android Application  where you can view profiles of the ones using the app and can be able to communicate with them by chatting using messages and maybe plan for a  date. 

### Table of contents
 * General info.
 * Technologies.
 * Setup.
 * BDD.
 * Input.
 * Output.
 * Code Examples.
 * Status.
 * Inspiration.
 * Contact.
 * Licence.
 ### General info
This project is called Date_me_APP, It is an Android application which will allow the user to choose a certain person according to his/her choice and then be able to chat with that person.

### Technologies
Project is created with: * Java.
                         * Android.
                         * Firebase.
### Setup
To run this project,you may have android studio in you machine :
- android studio.
- install emulator for you to run application or you can use USB to run it to your phone.
- then to get all source code locally these are some git command you may use $ cd ../git init $ git clone https://github.com/RuzindanaWendyOrnella/Date-me-App.git.

### BDD
 ## Behavior
 * Enter the required information for the signup and login.
 * Then click on next button bellow which will direct you to second activity and there you can click on a person's profile to get more details. 
 

 ## Input
 * Signup first if you don't have an account or Login for the one who has an account.
 
 ## Output
 * list of all the people who uses the app.  
 * Profile or details of that user or the chosen user.
 * And then the user can chat with the one of his/her choice.
 

 ## Code Examples

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle(  user.getDisplayName());
                } else {                }
            }
        };
        TabLayout tableLayout=(findViewById(R.id.tab_layout));
        ViewPager viewPager=(findViewById(R.id.view_pager));
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatsFragment(),"Chats");
        viewPagerAdapter.addFragment(new UsersFragment(),"Users");
        viewPager.setAdapter(viewPagerAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent logIn = new Intent(MainActivity.this, LoginActivity.class);
        logIn.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logIn);
        finish();
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        ViewPagerAdapter(FragmentManager fm){
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String tittle){
            fragments.add(fragment);
            titles.add(tittle);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}

### Status
 This Project is done except in Play store but you can use it if you want in your phone by running it into your phone as it mentioned above.

### Inspiration
  All the Credits goes to Moringa School for their  contents which is well explained.

### Contact Details
 Created By ruzindanawendy@gmail.com.

## Licence
Copyright 2019. 
