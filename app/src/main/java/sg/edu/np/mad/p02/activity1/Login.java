package sg.edu.np.mad.p02.activity1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Users");
        DatabaseReference madchild = userRef.child("mad").push();
        DatabaseReference userRef2 = database.getReference("mad");
        DatabaseReference madchild2 = userRef2.child("password:").push();
        madchild2.setValue(11887);
        DatabaseReference madchild3 = userRef2.child("username:").push();
        madchild3.setValue("mad");

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://practical-6-1bdea-default-rtdb.asia-southeast1.firebasedatabase.app");

        Button loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userRef.child("password: ").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
                userRef.child("username:").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getChildren();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
                EditText username = findViewById(R.id.etUsername);
                EditText password = findViewById(R.id.etPassword);
                Integer fb_pw = Integer.valueOf(password.getText().toString());
                if(fb_pw == 11877)
                {
                    Intent LoginSuccess = new Intent(Login.this, ListActivity.class);
                    startActivity(LoginSuccess);
                }
                else {
                    Toast.makeText(Login.this,"Your username or password " +
                            "is invalid or incorrect.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}