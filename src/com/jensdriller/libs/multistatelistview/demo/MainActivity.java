package com.jensdriller.libs.multistatelistview.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.jensdriller.libs.multistatelistview.MultiStateListView;
import com.jensdriller.libs.multistatelistview.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button mBtnLoad0;
	private Button mBtnLoad10;
	private Button mBtnError;

	private MultiStateListView mMultiStateListView;
	private ArrayAdapter<String> mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mBtnLoad0 = (Button) findViewById(R.id.btnLoad0);
		mBtnLoad10 = (Button) findViewById(R.id.btnLoad10);
		mBtnError = (Button) findViewById(R.id.btnError);

		mBtnLoad0.setOnClickListener(this);
		mBtnLoad10.setOnClickListener(this);
		mBtnError.setOnClickListener(this);

		mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
		mMultiStateListView = (MultiStateListView) findViewById(R.id.list);
		mMultiStateListView.setAdapter(mListAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnLoad0:
				new LoadListDataTask().execute(0);
				break;

			case R.id.btnLoad10:
				new LoadListDataTask().execute(10);
				break;

			case R.id.btnError:
				new LoadListDataTask().execute(-1);
				break;
		}
	}

	private class LoadListDataTask extends AsyncTask<Integer, Void, List<String>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mListAdapter.clear();
			mListAdapter.notifyDataSetChanged();
			mMultiStateListView.showLoadingView();
		}

		@Override
		protected List<String> doInBackground(Integer... params) {

			try { // simulate loading task...
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int count = params[0];
			if (count == -1) {
				return null;
			}

			List<String> dummyData = new ArrayList<String>();
			for (int i = 1; i <= count; i++) {
				dummyData.add("#" + i);
			}

			return dummyData;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);

			if (result == null) {
				mMultiStateListView.showErrorView();
			} else if (result.isEmpty()) {
				mMultiStateListView.showEmptyView();
			} else {
				mListAdapter.addAll(result);
				mListAdapter.notifyDataSetChanged();
			}
		}
	}

}
