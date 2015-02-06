package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submit(View v){

        TextView coursecode = (TextView)findViewById(R.id.etCode);
        TextView credit = (TextView)findViewById(R.id.etCR);
        RadioGroup rg = (RadioGroup)findViewById(R.id.groupg);
        RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());

        Intent res = new Intent();
        res.putExtra("cc", coursecode.getText().toString());
        res.putExtra("credit", credit.getText().toString());
        res.putExtra("grade", rb.getText().toString());
        setResult(RESULT_OK, res);
        finish();
    }
}
