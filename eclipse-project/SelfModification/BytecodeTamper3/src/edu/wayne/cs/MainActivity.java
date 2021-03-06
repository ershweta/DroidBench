package edu.wayne.cs;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

/**
 * @testcase_name BytecodeTamper3
 * @author Wayne State University,
 * @author_mail zhenyu.ning@wayne.edu
 * 
 * @description Both source and sink are created by self-modified code.
 * @dataflow getSource: source -> maliciousMethod -> sink
 * @number_of_leaks 1
 * @challenges Sophisticated opponent could use self-modified code to achieve
 *             malicious behavior.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv1)).setText(NativeInterface.jniTest());
        String info = getOtherThings();
        benignMethod(info);
    }

    public String getSource() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public String getOtherThings() {
        return "";
    }

    public void benignMethod(String param) {
        Log.e("DroidBench", "FAKE");
    }

    public void maliciousMethod(String param) {
        Log.e("DroidBench", param);
    }
}
