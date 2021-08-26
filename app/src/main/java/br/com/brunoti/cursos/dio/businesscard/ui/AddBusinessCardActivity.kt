package br.com.brunoti.cursos.dio.businesscard.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.brunoti.cursos.dio.businesscard.App
import br.com.brunoti.cursos.dio.businesscard.R
import br.com.brunoti.cursos.dio.businesscard.data.BusinessCard
import br.com.brunoti.cursos.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import com.divyanshu.colorseekbar.ColorSeekBar


class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {

        binding.colorSeekBarBg.setOnColorChangeListener(object: ColorSeekBar.OnColorChangeListener{
            override fun onColorChangeListener(color: Int) {
                binding.tilPrevia.editText?.setBackgroundColor(color)
            }
        })

        binding.colorSeekBarFont.setOnColorChangeListener(object: ColorSeekBar.OnColorChangeListener{
            override fun onColorChangeListener(color: Int) {
                binding.tilPrevia.editText?.setTextColor(color)
            }
        })

        binding.btnConfirm.setOnClickListener {

            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                bgPersonalizado = binding.colorSeekBarBg.getColor().toString(),
                fontPersonalizado = binding.colorSeekBarFont.getColor().toString()
            )


            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

}