package id.ac.ubaya.informatika.todokpc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todokpc.R
import id.ac.ubaya.informatika.todokpc.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick : (uuid:Int) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checkTask.text = todoList[position].title
        checkTask.isChecked = false
        checkTask.setText(todoList[position].title.toString())
        checkTask.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                adapterOnClick(todoList[position].uuid)
            }
        }

        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener {
            val uuid = todoList[position].uuid
            val action = TodoListFragmentDirections.todoListToEditTodo(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return todoList.size
    }
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}