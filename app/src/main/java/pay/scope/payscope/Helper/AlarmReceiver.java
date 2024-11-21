package pay.scope.payscope.Helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import pay.scope.payscope.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // This will be called when the alarm goes off
        Toast.makeText(context, "Alarm went off!", Toast.LENGTH_LONG).show();

        // Play sound
//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.your_sound_file); // Replace "your_sound_file" with the name of your sound file
//        mediaPlayer.start();

        // You can also start an activity or a service from here if needed
    }
}