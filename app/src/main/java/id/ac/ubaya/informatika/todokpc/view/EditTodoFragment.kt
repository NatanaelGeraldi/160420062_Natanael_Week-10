package id.ac.ubaya.informatika.todokpc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.todokpc.R
import id.ac.ubaya.informatika.todokpc.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        var txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        txtJudulTodo.text = "Edit Todo"

        var btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.text = "Save"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        val is_done = EditTodoFragmentArgs.fromBundle(requireArguments()).isDone
        btnAdd.setOnClickListener{

            val txtTitle = view.findViewById<EditText>(R.id.txtInputTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtInputNote)
            val rd = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(rd.checkedRadioButtonId)

            viewModel.update(txtTitle.text.toString(),txtNotes.text.toString(),radio.tag.toString().toInt(),uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        viewModel.fetch(uuid)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {

            val txtTitle = view?.findViewById<EditText>(R.id.txtInputTitle)
            val txtNotes = view?.findViewById<EditText>(R.id.txtInputNote)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

            val high = view?.findViewById<RadioButton>(R.id.radioHigh)
            val med = view?.findViewById<RadioButton>(R.id.radioMedium)
            val low = view?.findViewById<RadioButton>(R.id.radioLow)

            when (it.priority) {
                1 -> low?.isChecked = true
                2 -> med?.isChecked = true
                3 -> high?.isChecked = true
            }
            when (it.is_done){
                
            }

        })
    }




}