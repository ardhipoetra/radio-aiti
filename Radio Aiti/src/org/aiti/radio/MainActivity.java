package org.aiti.radio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	//in this case, prambors
	public static final String LINK_RADIO_EX = "http://liveradio.masima.co.id:8000/prambors";

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((TextView) findViewById(R.id.linkName)).setText(LINK_RADIO_EX);


		try {
			MediaPlayer p = setMethodSatu(); //setMethodDua();
			setButtonListener(p);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	private MediaPlayer setMethodSatu() throws Exception{
		MediaPlayer pl = new MediaPlayer();
		pl.setDataSource(LINK_RADIO_EX);
		pl.setAudioStreamType(AudioManager.STREAM_MUSIC);
		pl.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				StringBuilder sb = new StringBuilder();
				sb.append("error: ");
				switch (what) {
				case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
					sb.append("ga cocok buat progressive");
					break;
				case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
					sb.append("Server Die");
					break;
				case MediaPlayer.MEDIA_ERROR_UNKNOWN:
					sb.append("Geje");
					break;
				default:
					sb.append(" Sesuatu : Error COde(");
					sb.append(what);
					sb.append(")");
				}
				sb.append(" (" + what + ") ");
				sb.append(extra);
				sb.append('\n');

				System.out.println(sb.toString());
				return true;
			}
		});
		pl.prepareAsync();
		pl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) { 
				mp.start();
			}
		});

		return pl;

	}

	private void setMethodDua() { //kalau ada aja sih

	}

	private void setButtonListener(MediaPlayer p) {

		final MediaPlayer x = p;
		Button startButton = (Button) (findViewById(R.id.stButton));
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					setMethodSatu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button stopButton = (Button) (findViewById(R.id.spButton));
		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				x.stop();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}
