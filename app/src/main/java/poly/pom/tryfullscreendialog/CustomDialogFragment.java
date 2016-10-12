package poly.pom.tryfullscreendialog;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by User on 12/10/2016.
 */

public class CustomDialogFragment extends DialogFragment {
    private Bundle bundle;
    private LinearLayout upLayout;
    private LinearLayout downLayout;
    private LinearLayout pointerLayout;

    public static CustomDialogFragment newInstance(View aView) {

        Bundle args = new Bundle();

        CustomDialogFragment fragment = new CustomDialogFragment();
        int[] locations = new int[2];
        aView.getLocationInWindow(locations);
        int height = aView.getHeight();
        args.putInt("x", locations[0]);
        args.putInt("y", locations[1]);
        args.putInt("height", height);
        fragment.setArguments(args);
        return fragment;
    }

    private int getupLayoutHeight() {
        return getArguments().getInt("y") - getActionBarHeight() - getStatusBarHeight()+10;
    }

    private int getDownLayoutHeight() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        int viewHeight=getArguments().getInt("height");
        int viewYLocation= getArguments().getInt("y");
        int fix=10;
        return screenHeight -viewHeight - viewYLocation-10;
    }

    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */

    public int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return contentViewTop - statusBarHeight;
    }

    public int getActionBarHeight() {
        final TypedArray ta = getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarHeight = (int) ta.getDimension(0, 0);
        return actionBarHeight;
    }

    @Override
    public void onResume() {
        super.onResume();
        upLayout.getLayoutParams().height = getupLayoutHeight();

        downLayout.getLayoutParams().height = getDownLayoutHeight();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View view = inflater.inflate(R.layout.purchase_items, container, false);
        bundle = getArguments();
        upLayout = (LinearLayout) view.findViewById(R.id.layoutUp);
        downLayout = (LinearLayout) view.findViewById(R.id.layoutDown);
        pointerLayout = (LinearLayout) view.findViewById(R.id.layoutPointer);
        return view;

    }


    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
