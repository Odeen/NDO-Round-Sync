package ca.pkay.rcloneexplorer.Settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ca.pkay.rcloneexplorer.R
import de.felixnuesse.extract.settings.preferences.ButtonPreference
import es.dmoral.toasty.Toasty


class NotificationPreferencesFragment : PreferenceFragmentCompat() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_notification_preferences, rootKey)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        requireActivity().title = getString(R.string.notifications_pref_title)

        val notificationSettings = findPreference<Preference>("TempKeyNotificationSettings") as ButtonPreference
        notificationSettings.setButtonText(getString(R.string.open_notification_settings_button))
        notificationSettings.setButtonOnClick {
            val intent = Intent()
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS")

            //for Android 5-7
            intent.putExtra("app_package", requireContext().packageName)
            intent.putExtra("app_uid", requireContext().applicationInfo.uid)


            // for Android O
            intent.putExtra("android.provider.extra.APP_PACKAGE", requireContext().packageName)
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            } else {
                Toasty.error(requireContext(), "Couldn't find activity to start", Toast.LENGTH_SHORT, true).show()
            }
        }
    }
}