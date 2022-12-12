<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Traits\AdminTrait;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\App;

class DashboardController extends Controller
{
    use AdminTrait;

    public function index()
    {

        return view('admin.dashboard',array(
            'judul' => "Dashboard Administrator | ".$this->namaApps,
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
            'dataUser' => $this->users,
        ));
    }
}
