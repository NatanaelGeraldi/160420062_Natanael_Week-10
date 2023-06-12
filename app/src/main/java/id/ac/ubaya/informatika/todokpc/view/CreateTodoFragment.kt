package id.ac.ubaya.informatika.todokpc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.todokpc.R
import id.ac.ubaya.informatika.todokpc.model.Todo
import id.ac.ubaya.informatika.todokpc.viewmodel.DetailTodoViewModel


class CreateTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.txtInputTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtInputNote)
            val rd = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(rd.checkedRadioButtonId)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt())
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

}