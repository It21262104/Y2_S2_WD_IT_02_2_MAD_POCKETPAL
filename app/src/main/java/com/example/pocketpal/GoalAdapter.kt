
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        abstract fun onItemClick(key: String)
    }

    // Declare a listener variable
    var onItemClickListener: OnItemClickListener? = null

    var onDeleteClickListener: OnDeleteClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.title_textview)
        val deleteBtn: ImageButton = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.goal_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = goals.entries.elementAt(position).value
        val title = goal["title"].toString()
        holder.titleTextView.text = title

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(
                goals.entries.elementAt(position).key, // Key
                goal["title"].toString(), // Title
                goal["duration"].toString(), // Duration
                goal["amount"].toString(), // Amount
                goal["targetDate"].toString() // Target Date
            )
        }

        holder.deleteBtn.setOnClickListener {
            onDeleteClickListener?.onItemClick(
                goals.entries.elementAt(position).key, // Key
            )
        }
    }

    override fun getItemCount() = goals.size
}

