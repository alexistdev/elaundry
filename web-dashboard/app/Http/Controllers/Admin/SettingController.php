<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Traits\AdminTrait;
use Illuminate\Http\Request;

class SettingController extends Controller
{
    use AdminTrait;

    public function index()
    {

        return view('admin.setting',array(
            'judul' => "Dashboard Administrator | ".$this->namaApps,
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
            'dataUser' => $this->users,
        ));
    }
}
