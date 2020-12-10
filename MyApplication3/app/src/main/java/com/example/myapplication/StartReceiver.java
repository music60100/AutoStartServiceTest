package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class StartReceiver extends BroadcastReceiver {

    // region Consts

    private static final int NOTIFICATION_ID = 102;
    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL_ID_102";
    private static final String CHANNEL_NAME = "NOTIFICATION_CHANNEL_NAME_102";
    private static final String CHANNEL_DESCRIPTION = "App Notification Channel";
    private static final String NOTIFICATION_TITLE = "App is opened";

    // endregion

    // region BroadcastReceiver

    @Override
    public void onReceive(Context context, Intent intent) {

        // Save received action.
        String action = intent.getAction();

        // Make sure action is not null, then compare it to desired action:
        // Device's boot complete action or apps custom action to register receiver.
        if(action != null && action.equals(Intent.ACTION_LOCKED_BOOT_COMPLETED)){

            // Create notification.
            CreateAppNotification(context);

        }
    }

    // endregion

    // region Private Methods

    /**
     * Method creates notification informing we're in App state.
     *
     * @param context using to create notification (notification channel, notification builder etc).
     */
    public void CreateAppNotification(Context context) {

        // Create notification channel (using for new devices).
        CreateNotificationChannel(context);

        // Create notification informing about boot completion.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        // Create main notification.
        builder.setContentTitle(NOTIFICATION_TITLE)
                .setPriority(Notification.PRIORITY_MAX)
                .setShowWhen(true)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification);
//                .setColor()

        // Set action to open main activity when clicking on notification.
        Intent openActivityIntent = new Intent(context, MainActivity.class);
        openActivityIntent.setAction(Consts.START_APP_FROM_NOTIFICATION_ACTION);

        PendingIntent openActivityPendingIntent =
                PendingIntent.getActivity(context, 0, openActivityIntent, 0);
        builder.setContentIntent(openActivityPendingIntent);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Cteate notification channel (use for new devices).
     *
     * @param context uses to get notification manager(which helps creating channel).
     */
    private void CreateNotificationChannel(Context context) {

        // Create notification channel (required for OREO+ devices).
        NotificationChannel channel =
                new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        // Set notification description.
        channel.setDescription(CHANNEL_DESCRIPTION);

        // Save manager to create channel.
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.createNotificationChannel(channel);
    }

    // endregion
}