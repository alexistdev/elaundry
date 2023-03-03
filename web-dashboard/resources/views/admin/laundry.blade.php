<x-upcube.admin-template>
    @push('customCSS')
        <!-- DataTables -->
        <link href="{{asset('template/upcube/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css')}}"
              rel="stylesheet" type="text/css"/>
        <link href="{{asset('template/upcube/assets/libs/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css')}}"
              rel="stylesheet" type="text/css"/>
        <link href="{{asset('template/upcube/assets/libs/datatables.net-select-bs4/css//select.bootstrap4.min.css')}}"
              rel="stylesheet" type="text/css"/>

        <!-- Responsive datatable examples -->
        <link
            href="{{asset('template/upcube/assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css')}}"
            rel="stylesheet" type="text/css"/>
    @endpush
    <!-- start page title -->
    <div class="row">
        <div class="col-12">
            <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                <h4 class="mb-sm-0">Data Laundry</h4>

                <div class="page-title-right">
                    <ol class="breadcrumb m-0">
                        <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item active">Laundry</li>
                    </ol>
                </div>

            </div>
        </div>
    </div>
    <!-- end page title -->


    <div class="row">
        <div class="col-xl-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalTambah">
                                Tambah
                            </button>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table id="tabelLaundry" class="table table-striped table-bordered" style="width:100%">
                                    <thead class="table-light">
                                    <tr>
                                        <th class="text-center">No.</th>
                                        <th class="text-center">Name</th>
                                        <th class="text-center">Phone</th>
                                        <th class="text-center">Alamat</th>
                                        <th class="text-center">Status</th>
                                        <th class="text-center">Action</th>
                                    </tr>
                                    </thead><!-- end thead -->
                                    <tbody>
                                    </tbody>
                                </table> <!-- end table -->
                            </div>
                        </div>
                    </div>


                </div><!-- end card -->
            </div><!-- end card -->
        </div>
        <!-- end col -->
    </div>
    <!-- end row -->

    <!-- Start: Modal Tambah -->
    <div id="modalTambah" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-standard-title"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modal-standard-title">Tambah Laundry</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div> <!-- // END .modal-header -->
                <form action="{{route('adm.laundry.add')}}" method="post">
                    @csrf
                    <div class="modal-body">
                        <!-- Start : Nama User -->
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="namaPengguna"
                                       class="form-label labelError  @error('namaPengguna')text-danger @enderror">Nama
                                    Pengguna</label>
                                <input class="form-control inputError @error('namaPengguna')is-invalid @enderror"
                                       type="text" name="namaPengguna" placeholder="Nama Pengguna"
                                       value="{{old('namaPengguna')}}"
                                       id="namaPengguna"/>
                                @error('namaPengguna')
                                <p class="text-danger errorMessage">{{ $message }}</p>
                                @enderror
                            </div>
                        </div>
                        <!-- End : Nama User -->
                        <!-- Start : Email -->
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="emailPengguna"
                                       class="form-label labelError  @error('emailPengguna')text-danger @enderror">Email</label>
                                <input class="form-control inputError @error('emailPengguna')is-invalid @enderror"
                                       type="email" name="emailPengguna" placeholder="Email"
                                       value="{{old('emailPengguna')}}"
                                       id="emailPengguna"/>
                                @error('emailPengguna')
                                <p class="text-danger errorMessage">{{ $message }}</p>
                                @enderror
                            </div>
                        </div>
                        <!-- End : Email-->
                        <!-- Start : Nama Laundry -->
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="namaLaundry"
                                       class="form-label labelError  @error('namaLaundry')text-danger @enderror">Nama
                                    Laundry</label>
                                <input class="form-control inputError @error('namaLaundry')is-invalid @enderror"
                                       type="text" name="namaLaundry" placeholder="Nama Laundry"
                                       value="{{old('namaLaundry')}}"
                                       id="namaLaundry"/>
                                @error('namaLaundry')
                                <p class="text-danger errorMessage">{{ $message }}</p>
                                @enderror
                            </div>
                        </div>
                        <!-- End : Nama Laundry-->

                        <!-- Start : Phone -->
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="phone"
                                       class="form-label labelError  @error('phone')text-danger @enderror">Phone</label>
                                <input class="form-control inputError @error('phone')is-invalid @enderror"
                                       type="text" name="phone" placeholder="Phone" value="{{old('Phone')}}"
                                       id="phone"/>
                                @error('phone')
                                <p class="text-danger errorMessage">{{ $message }}</p>
                                @enderror
                            </div>
                        </div>
                        <!-- End : Phone -->

                        <!-- Start : Alamat -->
                        <div class="row">
                            <div class="col-lg-12">
                                <label for="alamat"
                                       class="form-label labelError  @error('alamat')text-danger @enderror">Alamat</label>
                                <input class="form-control inputError @error('alamat')is-invalid @enderror"
                                       type="text" name="alamat" placeholder="Alamat" value="{{old('alamat')}}"
                                       id="alamat"/>
                                @error('alamat')
                                <p class="text-danger errorMessage">{{ $message }}</p>
                                @enderror
                            </div>
                        </div>

                        <!-- End : Alamat -->
                        <div class="row mt-5">
                            <div class="col-lg-12">
                                <span class="text-danger text-sm">* password otomatis: 1234</span>
                            </div>

                        </div>

                    </div> <!-- // END .modal-body -->
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-primary">Tambah</button>
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Batal</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div> <!-- // END .modal-content -->
        </div> <!-- // END .modal-dialog -->
    </div>
    <!-- End: Modal Tambah        -->

    <!-- Start: Modal Hapus -->
    <div id="modalHapus" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-standard-title"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modal-standard-title">Hapus data</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div> <!-- // END .modal-header -->
                <form action="{{route('adm.laundry.delete')}}" method="post">
                    @csrf
                    @method('delete')
                    <div class="modal-body">
                        <input type="hidden" id="hapusId" name="user_id">

                      apakah anda ingin menghapus data ini

                    </div> <!-- // END .modal-body -->
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-danger">Hapus</button>
                                <button type="button" class="btn btn-default" data-bs-dismiss="modal">Batal</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div> <!-- // END .modal-content -->
        </div> <!-- // END .modal-dialog -->
    </div>
    <!-- End: Modal Hapus        -->
    @push('customJS')

        <!-- Required datatable js -->
        <script src="{{asset('template/upcube/assets/libs/datatables.net/js/jquery.dataTables.min.js')}}"></script>
        <script
            src="{{asset('template/upcube/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js')}}"></script>
        <!-- Responsive examples -->
        <script
            src="{{asset('template/upcube/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js')}}"></script>
        <script
            src="{{asset('template/upcube/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js')}}"></script>

        <script>
            $(document).ready(function () {
                let base_url = '{{route('adm.laundry')}}';

                $('.modal').on('hidden.bs.modal', function (e) {
                    e.preventDefault();
                    let pesanError = $('.errorMessage');
                    let errorInput = $('.errorInput');
                    let errorLabel = $('.errorLabel');
                    pesanError.html("");
                    errorInput.removeClass('is-invalid');
                    errorLabel.removeClass('text-danger');
                })

                /** saat tombol hapus di klik */
                $(document).on("click", ".open-hapus", function (e) {
                    e.preventDefault();
                    let fid = $(this).data('id');
                    $('#hapusId').val(fid);
                })

                $('#tabelLaundry').DataTable({
                    responsive: true,
                    processing: true,
                    serverSide: true,
                    ajax: {
                        type: 'GET',
                        url: base_url,
                        async: true,
                        dataType: 'json',
                    },
                    columns: [
                        {
                            data: 'index',
                            class: 'text-center',
                            defaultContent: '',
                            orderable: false,
                            searchable: false,
                            width: '5%',
                            render: function (data, type, row, meta) {
                                return meta.row + meta.settings._iDisplayStart + 1; //auto increment
                            }
                        },
                        {data: 'nama_laundry', class: 'text-left'},
                        {data: 'phone', class: 'text-left'},
                        {data: 'alamat', class: 'text-left'},
                        {data: 'status', class: 'text-center'},
                        {data: 'action', class: 'text-center', width: '20%', orderable: false},
                    ],
                    "bDestroy": true
                });
            });
        </script>
    @endpush
</x-upcube.admin-template>
