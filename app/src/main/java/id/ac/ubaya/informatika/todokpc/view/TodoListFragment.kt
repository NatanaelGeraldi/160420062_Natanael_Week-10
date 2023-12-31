package id.ac.ubaya.informatika.todokpc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ubaya.informatika.todokpc.R
import id.ac.ubaya.informatika.todokpc.viewmodel.ListTodoViewModel


class TodoListFragment : Fragment() {
    private lateinit var viewModel: ListTodoViewModel
    private val todoListAdapter = TodoListAdapter(arrayListOf(),{ uuid -> viewModel.update_isDone(uuid)})
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        var recViewTodo = view.findViewById<RecyclerView>(R.id.recViewTodo)
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        val fab = view.findViewById<FloatingActionButton    >(R.id.fabAddTodo)

        fab.setOnClickListener {
            val action = TodoListFragmentDirections.todoListToCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }
    fun observeViewModel(){

        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)

            var txtEmpty = view?.findViewById<TextView>(R.id.txtEmpty)

            if(it.isEmpty()) {
                txtEmpty?.visibility = View.VISIBLE
            } else {
                txtEmpty?.visibility = View.GONE
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

}