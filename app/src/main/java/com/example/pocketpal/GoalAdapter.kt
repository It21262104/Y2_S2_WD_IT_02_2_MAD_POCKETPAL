import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketpal.R

class GoalAdapter(private val goals: Map<String, Map<String, Any>>) :
    RecyclerView.Adapter<GoalAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(key: String, title: String, duration: String, amount: String, targetDate: String)
    }

    interface OnDeleteClickListener {
        fun onItemClick(key: String)
    }

    interface OnCompletionClickListener {
        fun onItemClick(key: String, completed: Boolean)
    }

    private lateinit var context: Context

    var onItemClickListener: OnItemClickListener? = null
    var onDeleteClickListener: OnDeleteClickListener? = null
    var onCompletionClickListener: OnCompletionClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.title_textview)
        val deleteBtn: ImageButton = view.findViewById(R.id.delete_button)
        val completedCheckbox: CheckBox = view.findViewById(R.id.completed_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.goal_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = goals.entries.elementAt(position).value
        val key = goals.entries.elementAt(position).key
        val title = goal["title"].toString()

        holder.titleTextView.text = title

        // Set the checkbox status based on the completion status of the goal
        val isCompleted = goal["completed"] as? Boolean ?: false
        holder.completedCheckbox.isChecked = isCompleted

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(
                key,
                title,
                goal["duration"].toString(),
                goal["amount"].toString(),
                goal["targetDate"].toString()
            )
        }

        holder.deleteBtn.setOnClickListener {
            showDeleteConfirmationDialog(key)
        }

        holder.completedCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showDeletePromptDialog(key)
            } else {
                onCompletionClickListener?.onItemClick(key, isChecked)
            }
        }
    }

    override fun getItemCount() = goals.size

    private fun showDeleteConfirmationDialog(key: String) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Delete Goal")
            .setMessage("Are you sure you want to delete this goal?")
            .setPositiveButton("Delete") { _, _ ->
                onDeleteClickListener?.onItemClick(key)
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun showDeletePromptDialog(key: String) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Delete Goal")
            .setMessage("Congratulations on completing your goal! Do you want to delete it?")
            .setPositiveButton("Delete") { _, _ ->
                onDeleteClickListener?.onItemClick(key)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                // Uncheck the checkbox since the deletion was canceled
                // notifyDataSetChanged()
            }
            .create()

        alertDialog.show()
    }
}

