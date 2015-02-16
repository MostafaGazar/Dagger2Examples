package com.nullcognition.dagger2examples.sven.components.a;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullcognition.dagger2examples.R;
import com.nullcognition.dagger2examples.sven.inject.HasComponent;

import javax.inject.Inject;

public class AFragment extends Fragment {

   public interface Injector {

	  void inject(AFragment fragment);
   }

   @Inject
   SomeADependency mSomeADependency;

   public static AFragment newInstance(){
	  return new AFragment();
   }

   @Override
   public void onAttach(final Activity activity){
	  super.onAttach(activity);

	  ((HasComponent<Injector>)activity).getComponent()
										.inject(this);
   }

   @Override
   public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState){

	  final View view = inflater.inflate(R.layout.fragment_component, container, false);

	  final TextView textView = (TextView)view.findViewById(R.id.text);
	  textView.setText(mSomeADependency.getText());

	  return view;
   }
}
