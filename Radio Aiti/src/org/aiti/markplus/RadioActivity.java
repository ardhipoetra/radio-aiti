package org.aiti.markplus;

import org.aiti.markplus.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends Activity {

	//in this case, prambors
	public static final String LINK_RADIO_EX = "http://liveradio.masima.co.id:8000/prambors";
	public static final String LINK_RADIO_EX2 = "http://us.kradio.in:8888/";
	
	boolean isPlaying = false;
	MediaPlayer mpObj = null;
	String current = LINK_RADIO_EX;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_act);

		((TextView) findViewById(R.id.linkName)).setText(current);


		try {
			setMethodSatu(); //setMethodDua();
			setButtonListener();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	private void setMethodSatu() throws Exception{
		
		if (isPlaying) {
			Toast.makeText(RadioActivity.this, "Radio sedang terputar", Toast.LENGTH_SHORT).show();
			return;
		}
		
		AudioManager amanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = amanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		amanager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0); //set suara ke max :p
		
		mpObj = new MediaPlayer();
		mpObj.setDataSource(current);
		mpObj.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mpObj.setOnErrorListener(new MediaPlayer.OnErrorListener() {
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
		final ProgressDialog anu = new ProgressDialog(this);
		anu.setCancelable(false);
		anu.setTitle("Tunggu sejenak");
		anu.setMessage("Menyiapkan radio...");
		anu.show();
		mpObj.prepareAsync();
		mpObj.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				anu.dismiss();
				mp.start();
				isPlaying = true;
				
			}
		});

	}

	private void setMethodDua() { //kalau ada aja sih

	}

	private void setButtonListener() {

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
				mpObj.stop();
				isPlaying = false;
			}
		});
		
		Button gantiButton= (Button) (findViewById(R.id.gtButton));
		gantiButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mpObj.stop();
				isPlaying = false;
				
				if (current.equals(LINK_RADIO_EX))
					current = LINK_RADIO_EX2;
				else
					current = LINK_RADIO_EX;
				
				((TextView) findViewById(R.id.linkName)).setText(current);
				
				try {
					setMethodSatu();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		AlertDialog x = new AlertDialog.Builder(this).
				setMessage("Yakin mau keluar?").
				setTitle("Peringatan!").
				setPositiveButton("yaqin", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mpObj.stop();
						isPlaying = false;
						System.exit(0);
					}
				}).
				setNegativeButton("NO", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				}).
				show();
	}
	
	


}
