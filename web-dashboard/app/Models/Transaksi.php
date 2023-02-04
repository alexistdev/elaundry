<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Transaksi extends Model
{
    use HasFactory;
    protected $fillable=[
        'user_id','store_id','satuan','total','phone','longitude','latitude','tgl_selesai'
    ];

    protected $casts = [
        'created_at'  => 'date:d-m-Y',
    ];

    public function store(){
        return $this->belongsTo(Store::class)->select('id','nama_laundry');
    }

    protected function status(): Attribute
    {
        return Attribute::make(
            get: fn ($value) => $this->getStatus($value),
        );
    }

    private function getStatus($status):string
    {
        if($status == 0){
            $str = "pending";
        } else if($status == 1){
            $str = "jemput";
        } else if($status == 2){
            $str = "dicuci";
        } else if($status == 3){
            $str = "diantar";
        } else if($status == 4){
            $str = "selesai";
        } else {
            $str = "dibatalkan";
        }
        return $str;
    }
}
