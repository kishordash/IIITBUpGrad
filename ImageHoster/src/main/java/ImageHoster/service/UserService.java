package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    //Validate password method to heck on the strength of the password entered by the user
    //at the time of registration. This method checks for having at least the length should be of 3 and
    // should have at least one Letter (Upper/Lower case), one number (0 - 9) and one Special Character.
    public boolean validatePassword(User user) {


        if (user.getPassword().isEmpty())
            return false;
        else if (user.getPassword().length() < 3)
            return false;
        else {
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}";
            return user.getPassword().matches(pattern);

            /*int charCount = 0;
            int numCount = 0;
            int splCharCount = 0;
            for (int i = 0; i < user.getPassword().length(); i++) {
                char ch = user.getPassword().charAt(i);
                if (is_Numeric(ch)) numCount++;
                else if (is_Letter(ch)) charCount++;
                else if (is_SpecialChar(ch))
                    splCharCount++;
                }


            return (numCount >= 1 && charCount >= 1 && splCharCount >= 1);
        }
    }

    private boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    private boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }

    private boolean is_SpecialChar(Char ch){
        String pattern = "(?=.*[@#$%^&+=])";
        return ch.matches(pattern);

    }*/
        }
    }
}


