package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();
        calculate();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void buttonClicked(View v) {
        Intent i;
       switch (v.getId()) {
            case R.id.button4 :
                i = new Intent(this, CourseListActivity.class);
                i.putExtra("list", createlist());
                startActivity(i);
                break;

            case R.id.button2 :
                i = new Intent(this, CourseActivity.class);
                startActivityForResult(i, 1);
                break;

           case R.id.button :
               listCodes = new ArrayList<String>();
               listCredits = new ArrayList<Integer>();
               listGrades = new ArrayList<String>();
               cr =0;
               gp=0;
               gpa=0;
               calculate();

               break;

        }
    }


    public void calculate(){
        cr=0;
        gp=0;
        gpa=0;
        for(int i=0 ; i < listGrades.size() ; i++){
            double gradep=0;
            switch (listGrades.get(i)){
                case "A" :
                    gradep = 4.0*listCredits.get(i);
                    break;
                case "B+" :
                    gradep = 3.5*listCredits.get(i);
                    break;
                case "B" :
                    gradep = 3.0*listCredits.get(i);
                    break;
                case "c+" :
                    gradep = 2.5*listCredits.get(i);
                    break;
                case "C" :
                    gradep = 2.0*listCredits.get(i);
                    break;
                case "D+" :
                    gradep = 1.5*listCredits.get(i);
                    break;
                case "D" :
                    gradep = 1.0*listCredits.get(i);
                    break;
                case "F" :
                    gradep = 0*listCredits.get(i);
                    break;
            }
            gp += gradep;
            cr += listCredits.get(i);
        }
        gpa = gp/cr;
        TextView gradepoints = (TextView)findViewById(R.id.tvGP);
        gradepoints.setText(Double.toString(gp));

        TextView credits = (TextView)findViewById(R.id.tvCR);
        credits.setText(Integer.toString(cr));

        TextView CGPA = (TextView)findViewById(R.id.tvGPA);
        CGPA.setText(String.format("%.2f",gpa));    //change to string and set the decimal to be .00
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //String result = data.getStringExtra("retValue");
                //tvResult.setText(result);
                listCodes.add(data.getStringExtra("cc"));
                listCredits.add(Integer.parseInt(data.getStringExtra("credit")));
                listGrades.add(data.getStringExtra("grade"));
                calculate();
            }
            else if (resultCode == RESULT_CANCELED) {
                //tvResult.setText("CANCELED");
                Toast t = Toast.makeText(this,"Canceled adding subject",Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public String createlist(){
        String allcourse="List of Courses\n";
        for(int i =0 ; i < listCodes.size() ; i++){
            allcourse = allcourse + listCodes.get(i) + "(" +listCredits.get(i) + "Credits)" + " = " + listGrades.get(i) + "\n";
        }
        return allcourse;
    }


}
