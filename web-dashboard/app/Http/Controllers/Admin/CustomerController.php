<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Traits\AdminTrait;
use App\Models\Kategori;
use App\Models\User;
use Illuminate\Http\Request;
use Yajra\DataTables\DataTables;

class CustomerController extends Controller
{
    use AdminTrait;

    public function index(Request $request)
    {
        if ($request->ajax()) {
            $customer = User::with('pelanggan')->orderBy('id', 'desc')->customer()->get();
            return DataTables::of($customer)
                ->addIndexColumn()
                ->editColumn('created_at', function ($request) {
                    return $request->created_at->format('d-m-Y H:i:s');
                })
                ->editColumn('name', function ($request) {
                    return ucwords($request->pelanggan?->name);
                })
                ->editColumn('phone', function ($request) {
                    return $request->pelanggan?->phone;
                })
                ->editColumn('status', function ($request) {
                    if($request->status != 1){
                        $str = "Suspend";
                    } else {
                        $str = "Aktif";
                    }
                   return $str;
                })
                ->addColumn('action', function ($row) {
                    $url = "";
                    $btn = " <a href=\"$url\" class=\"btn btn-outline-danger open-hapus\" data-id=\"$row->id\" data-bs-toggle=\"modal\" data-bs-target=\"#modalHapus\"> Hapus</a>";
                    return $btn;
                })
                ->rawColumns(['action'])
                ->make(true);
        }
        return view('admin.customer', array(
            'judul' => "Dashboard Guru | FavoriteIDN",
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
        ));
    }
}
