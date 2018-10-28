package com.example.owner.nearplace;


import android.os.Looper;

import com.example.owner.nearplace.api.NearbyPlaceApiService;
import com.example.owner.nearplace.mapper.NearbyPlaceInfoMapper;
import com.example.owner.nearplace.mvp.model.NearbyPlace;
import com.example.owner.nearplace.mvp.model.NearbyPlaceInfo;
import com.example.owner.nearplace.mvp.model.Result;
import com.example.owner.nearplace.mvp.presenter.NearbyPlacePresenter;
import com.example.owner.nearplace.mvp.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class, Looper.class, NearbyPlace.class})
public class NearbyPlacePresenterTest {

    public static final String TEST_ERROR_MESSAGE = "error_message";

    @InjectMocks
    private NearbyPlacePresenter presenter;
    @Mock
    private NearbyPlaceApiService nearbyPlaceApiService;
    @Mock private NearbyPlaceInfo nearbyPlaceInfo;
    @Mock private NearbyPlaceInfoMapper nearbyPlaceInfoMapper;
    @Mock private MainView mView;
    @Mock private Observable<NearbyPlace> mObservable;

    @Captor
    private ArgumentCaptor<Subscriber<NearbyPlace>> captor;

    private final RxJavaSchedulersHook mRxJavaSchedulersHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        ArrayList<NearbyPlaceInfo> placeInfoArrayList = new ArrayList<>();
        placeInfoArrayList.add(new NearbyPlaceInfo());

    }

    @Test
    public void getMovies() throws Exception {
        PowerMockito.mockStatic(Looper.class);
        when(AndroidSchedulers.mainThread()).thenReturn(mRxJavaSchedulersHook.getComputationScheduler());

        when(nearbyPlaceApiService.getNearbyPlaces("-33.8670522,151.1957362","1500",BuildConfig.PLACE_API_KEY)).thenReturn(mObservable);
        presenter.getNearbyPlaces("-33.8670522,151.1957362","1500",BuildConfig.PLACE_API_KEY);
        verify(mView, atLeastOnce()).onShowDialog("loading...");
    }

    @Test
    public void onCompleted() throws Exception {
        presenter.onCompleted();
        verify(mView, times(1)).onHideDialog();
        verify(mView, times(1)).onShowToast("Places loading complete!");
    }

    @Test
    public void onError() throws Exception {
        presenter.onError(new Throwable(TEST_ERROR_MESSAGE));
        verify(mView, times(1)).onHideDialog();
        verify(mView, times(1)).onShowToast("Error loading places" + TEST_ERROR_MESSAGE);
    }

    @Test
    public void onNext() throws Exception {
        NearbyPlace response = mock(NearbyPlace.class);
        ArrayList<Result> results = new ArrayList<Result>();
        when(response.getResults()).thenReturn(results);
        presenter.onNext(response);
        verify(nearbyPlaceInfoMapper, times(1)).mapNearbyPlaceInfo( response);
        verify(mView, times(1)).onNearbyPlacesLoaded(new ArrayList<NearbyPlaceInfo>());

    }
}
