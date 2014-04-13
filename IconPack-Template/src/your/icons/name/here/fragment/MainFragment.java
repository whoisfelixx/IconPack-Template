package your.icons.name.here.fragment;


import java.util.ArrayList;
import java.util.List;

import view.ScrollGridView;
import your.icons.name.here.R;
import your.icons.name.here.activity.AboutThemeActivity;
import your.icons.name.here.activity.ApplyLauncherMain;
import your.icons.name.here.activity.RequestActivity;
import your.icons.name.here.activity.Wallpaper;
import your.icons.name.here.adapter.MainAdapter;
import your.icons.name.here.adapter.MainAdapter.AdapterItem;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;


/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class MainFragment extends SherlockFragment{
	
	ScrollGridView gridView;
	final List<AdapterItem> listOfStuff = new ArrayList<AdapterItem>();
	
	public static final int OSS = 0;
	public static final int THEMEINFO = 1;
	public static final int APPLY = 2;
	public static final int WALLPAPER = 3;
	public static final int REQUEST = 4;

	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.gridview_behind, null);
	}
	
	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	/* 
	 * This part does two things
	 * First - It counts the number of items and displays them
	 * Second - It displays the text in the "" which is a brief description of that item
	 * Removing any of these will remove that item but be sure to edit ALL the cases below or your list
	 * won't line up properly
	 */
		
		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove BOTH parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/

		/* 
		 * Sets the Title and description text for each GridView item
		 * Check res/values/strings.xml to change text to whatever you want each GridView to say
		 */
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_app), 
					getResources().getString (R.string.desc_oss), 0));
			listOfStuff.remove(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_request), 
					getResources().getString (R.string.desc_request), 4));

			
		} else {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_app), 
					getResources().getString (R.string.desc_oss), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_request), 
					getResources().getString (R.string.desc_request), 4));
		}

		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove both parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/
			MainAdapter adapter = new MainAdapter(getActivity(), listOfStuff);
	
			gridView.setAdapter(adapter);
			gridView.setExpanded(true);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					
					@SuppressWarnings("unused")
					MainFragment gridContentT = null;
					
					boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
					if (tabletSize) { // for tablets
						
						switch (position) {
						case OSS:
							/** 
							 ** This checks if MY OSS app is installed. You can remove this case
							 ** statement completely or add your own app to check against or leave
							 ** it and let it check for MY app :D
							 ** If it is installed, the app will open when you press the list item
							 ** If it is NOT installed, it will open up the play store to download it
							 ** Change line 136 with the play store link for your own app if you're 
							 ** using this feature!
							 **/
							if(isPackageExists("app.the1dynasty.oss")){
								Intent oss = new Intent("android.intent.action.MAIN");
								oss.setComponent(ComponentName.unflattenFromString
										("app.the1dynasty.oss/app.activities.MainActivity"));
								oss.addCategory("android.intent.category.LAUNCHER");
								startActivity(oss);
							}
							else{
								Intent oss = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
										("market://details?id=app.the1dynasty.oss"));
								startActivity(oss);
						}
			    			break;
						case APPLY:
							Intent launcher = new Intent(getSherlockActivity(), ApplyLauncherMain.class);
							startActivity(launcher);
			        		break;
						case WALLPAPER:
							Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
							startActivity(wall);
			        		break;
						case REQUEST:
							Intent request = new Intent(getSherlockActivity(), RequestActivity.class);
							startActivity(request);
							break;
		}	
				} else {	// for phones
					switch (position) {
					case OSS:
						/** 
						 ** This checks if MY OSS app is installed. You can remove this
						 ** section completely or Add your own app to check against or leave
						 ** it and let it check for MY app :D
						 ** If it is installed, the app will open when you press the list item
						 ** If it is NOT installed, it will open up the play store to download it
						 ** Change line 195 with the play store link for your own app if you're 
						 ** using this feature!
						 **/
						if(isPackageExists("app.the1dynasty.oss")){
							Intent oss = new Intent("android.intent.action.MAIN");
							oss.setComponent(ComponentName.unflattenFromString
									("app.the1dynasty.oss/app.activities.MainActivity"));
							oss.addCategory("android.intent.category.LAUNCHER");
							startActivity(oss);
						}
						else{
							Intent oss = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
									("market://details?id=app.the1dynasty.oss"));
							startActivity(oss);
					}
		    			break;
					case THEMEINFO:
						Intent aboutTheme = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutTheme);
		        		break;
					case APPLY:
						Intent launcher = new Intent(getSherlockActivity(), ApplyLauncherMain.class);
						startActivity(launcher);
		        		break;
					case WALLPAPER:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
		        		break;
					case REQUEST:
						Intent request = new Intent(getSherlockActivity(), RequestActivity.class);
						startActivity(request);
		        		break;
		        		
					}
				}
				}	
				
	/** 
	 ** This is the code needed to check the package in case 0
	 ** If you remove that check, you can remove this code too
	 ** Leaving it here won't harm anything either
	 **/
	public boolean isPackageExists(String targetPackage){
		  List<ApplicationInfo> packages;
		  PackageManager pm;
		  pm = getSherlockActivity().getPackageManager();
		  packages = pm.getInstalledApplications(0);
		  for (ApplicationInfo packageInfo : packages) {
		  if(packageInfo.packageName.equals(targetPackage)) return true;
		  }  
		  return false;
		  }
			});
			
	}
}